package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static com.wallee.android.till.sdk.data.Utils.requireNonNull;
import static com.wallee.android.till.sdk.data.Utils.check;

/**
 * One product or fee in the list of items that makes up a transaction.
 */
public class LineItem {
    private final String id;
    private final String sku;
    private final String name;
    private final BigDecimal quantity;
    private final LineItemType type;
    // Use the total including tax as the base. We can use this amount sum it up and we get the total. This way we have no issues with rounding / precision etc.
    private final BigDecimal totalAmountIncludingTax;
    private final BigDecimal discountIncludingTax;// This might be used to print receipts etc.

    private final Set<Tax> taxes;

    private LineItem(@NonNull String id, String sku, String name, @NonNull BigDecimal quantity, @NonNull LineItemType type, @NonNull BigDecimal totalAmountIncludingTax, @NonNull BigDecimal discountIncludingTax, @NonNull Set<Tax> taxes) {
        this.id = check(id, "id", 200);
        if (sku == null) {
            this.sku = id;
        } else {
            this.sku = check(sku, "sku", 200);
        }
        if (name == null) {
            this.name = id;
        } else {
            this.name = check(name, "name", 150);
        }
        this.quantity = check(quantity, "quantity");
        if (quantity.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("quantity must be zero or greater");
        this.type = requireNonNull(type, "type");
        this.totalAmountIncludingTax =  check(totalAmountIncludingTax, "totalAmountIncludingTax");
        this.discountIncludingTax = check(discountIncludingTax, "discountIncludingTax");
        this.taxes = Collections.unmodifiableSet(new HashSet<>(requireNonNull(taxes, "taxes")));
    }

    public String getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public LineItemType getType() {
        return type;
    }

    public BigDecimal getUnitPriceIncludingTax() {
        return this.totalAmountIncludingTax.divide(this.quantity);
    }

    public Set<Tax> getTaxes() {
        return taxes;
    }

    public BigDecimal getTotalAmountIncludingTax() {
        return this.totalAmountIncludingTax;
    }

    public static class ListBuilder {
        private final List<Builder> lineItems = new ArrayList<>();
        private Builder current;

        public ListBuilder(String id, BigDecimal unitPriceIncludingTax) {
            this.current = new Builder(id, unitPriceIncludingTax, this);
        }
        public ListBuilder(List<LineItem> lineItems) {
            for (LineItem lineItem : lineItems) {
                this.lineItems.add(new Builder(lineItem));
            }
            this.current = null;
        }
        public List<Builder> getLineItems() {
            return this.lineItems;
        }
        public Builder getCurrent() {
            return this.current;
        }
        public ListBuilder addNext(String id,  BigDecimal unitPriceIncludingTax) {
            this.lineItems.add(this.current);
            this.current = new Builder(id, unitPriceIncludingTax, this);
            return this;
        }

        public List<LineItem> build() {
            if (this.current != null)
                this.lineItems.add(this.current);
            List<LineItem> result = new ArrayList<>();
            for (Builder b : this.lineItems) {
                result.add(b.build());
            }
            return result;//this.lineItems.stream().map(b -> b.build()).collect(Collectors.toList());
        }
    }

    public static class Builder {
        private final String id;
        private final BigDecimal totalAmountIncludingTax;
        private String sku;
        private String name;
        private BigDecimal quantity = BigDecimal.ONE;
        private LineItemType type = LineItemType.PRODUCT;
        private BigDecimal discountIncludingTax = BigDecimal.ZERO;
        private Set<Tax> taxes = new HashSet<>();

        private ListBuilder listBuilder;

        public Builder(@NonNull String id, @NonNull BigDecimal totalAmountIncludingTax) {
            this(id, totalAmountIncludingTax, null);
        }
        private Builder(@NonNull String id, @NonNull BigDecimal totalAmountIncludingTax, @NonNull ListBuilder listBuilder) {
            this.id = id;
            this.totalAmountIncludingTax = totalAmountIncludingTax;
            this.listBuilder = listBuilder;
        }
        public Builder(@NonNull LineItem lineItem) {
            this(lineItem.id, lineItem.totalAmountIncludingTax, null);
            this.sku = lineItem.sku;
            this.name = lineItem.name;
            this.quantity = lineItem.quantity;
            this.type = lineItem.type;
            this.discountIncludingTax = lineItem.discountIncludingTax;
            this.taxes = new HashSet<>(lineItem.taxes);
        }
        public ListBuilder getListBuilder() {
            return this.listBuilder;
        }

        public Builder setSku(String sku) {
            this.sku = sku;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setQuantity(@NonNull BigDecimal quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setType(@NonNull LineItemType type) {
            this.type = type;
            return this;
        }

        public void setDiscountIncludingTax(@NonNull BigDecimal discountIncludingTax) {
            this.discountIncludingTax = discountIncludingTax;
        }

        public Builder addTax(String name, @NonNull BigDecimal rate) {
            this.taxes.add(new Tax(name, rate));
            return this;
        }

        public LineItem build() {
            return new LineItem(id, sku, name, quantity, type, totalAmountIncludingTax, discountIncludingTax, taxes);
        }
    }
}
