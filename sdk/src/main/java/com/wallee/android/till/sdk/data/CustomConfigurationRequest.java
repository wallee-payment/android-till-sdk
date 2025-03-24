package com.wallee.android.till.sdk.data;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

import androidx.annotation.NonNull;

public class CustomConfigurationRequest {
    private final String applicationId;

    private CustomConfigurationRequest(@NonNull String applicationId) {
        this.applicationId = requireNonNull(applicationId, "applicationId");
    }

    @NonNull
    public String getApplicationId() {
        return applicationId;
    }

    @NonNull
    @Override
    public String toString() {
        return "applicationId=" + applicationId;
    }

    public static class Builder {
        private String applicationId;

        public Builder(@NonNull String applicationId) {
            this.applicationId = applicationId;
        }

        public Builder(@NonNull CustomConfigurationRequest customConfigurationRequest) {
            this.applicationId = customConfigurationRequest.applicationId;
        }

        @NonNull
        public Builder setApplicationId(@NonNull String applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        @NonNull
        public CustomConfigurationRequest build() {
            return new CustomConfigurationRequest(this.applicationId);
        }
    }
}
