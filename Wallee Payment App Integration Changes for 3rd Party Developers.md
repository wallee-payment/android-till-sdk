### Wallee Payment App Integration Changes for 3rd Party Developers

**Overview:**
This document explains the upcoming changes to the Wallee Payment App integration for third-party apps for Android ATI. These changes will affect how third-party apps interact with the Android Till Interface (ATI).

The main goal of the document is to provide third-party developers clear instructions on how to ensure that the appropriate application (either Wallee or their own) is in the foreground at the right time and how to reliably configure features that impact screen behavior, such as showing or hiding transaction results.

### Key Changes:

1. **New Startup Application Configuration**
   - **StartupApplication Setting:** A new configuration parameter called `StartupApplication` will be added in the Terminal Software Manager, replacing the existing kiosk mode defined via the manifest file.
   - This allows the third-party app to be in the foreground until a transaction is initiated, after Wallee will manage the payment processing and return the original app to the foreground when complete.

2. **Deprecation of `PrimaryApplication` Configuration**
   - The existing `PrimaryApplication` setting in the Terminal Software Manager will be deprecated. Moving forward, instead of using `PrimaryApplication`, Wallee Payment App will now save the last application that was in the foreground before starting a transaction. This application will be brought to the front once the transaction is completed.

3. **Deprecation of Kiosk Mode in Android Till SDK**
   - The kiosk mode, previously defined via Android Till SDK manifest, will be deprecated. Integrators should migrate to the new `StartupApplication` method for managing app startup behavior.

4. **Addition of `ShowTrxResultScreens` Parameter**
   - The new `ShowTrxResultScreens` parameter will allow developers to control whether transaction results are shown on the screen.
   - Default behavior will be `True` (enabled), meaning that Wallee will show transaction result screens. If set to `False`, the third-party app will be responsible for displaying transaction results.

5. **Internal Handling of `TillMode`**
   - The `TillMode` parameter will no longer need to be included in the transaction request by integrators. Instead, it will be handled internally by Wallee to determine the type of integration (SDKApp, VTIApp, POSApp, WEBApp).
   - This simplifies integration, allows Wallee to determine the appropriate mode.

### Migration Notes:
- **Existing Behavior Support:** The current behavior involving `TillMode`, `PrimaryApplication`, and kiosk mode will continue to be supported temporarily during the transition phase to avoid disruptions. A timeline for complete deprecation will be provided.
- **Recommended Actions for Developers:**
  - Update your configuration to use the `StartupApplication` parameter in the Terminal Software Manager.
  - Migrate away from using `PrimaryApplication` and kiosk mode settings.
  - Ensure you include the `ShowTrxResultScreens` parameter in your transaction requests if your app needs to control whether transaction result screens are displayed.
