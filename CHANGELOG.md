# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

The SDK is following semantic versioning [Semantic Versioning 2.0.0](https://semver.org).


The key words **“MUST”, “MUST NOT”, “REQUIRED”, “SHALL”, “SHALL NOT”, “SHOULD”, “SHOULD NOT”, “RECOMMENDED”, “MAY”, and “OPTIONAL”** in this document are to be interpreted as described in [RFC 2119](https://tools.ietf.org/html/rfc2119).

## [2.1.0]

### Added
- **DisplayMessageSuppressionFlag transaction parameter**:
    - Added an optional flag for suppressing cardholder display messages during a transaction.
    - Omitting the flag preserves the existing terminal behaviour.

## [2.0.0]

### Added
- **StartupApplication parameter**:
    - Added new configuration parameter called `StartupApplication` in the Terminal Software Manager.
    - This parameter replaces the existing kiosk mode configuration and defines which application is launched automatically after terminal reboot.
    - Applies to all integration types (Android Till SDK, CTI, and LTI).
- **ShowTrxResultScreens parameter**:
    - Introduced `ShowTrxResultScreens` parameter to control whether transaction results are shown on the screen.
      The default is set to `True` (enabled), meaning Wallee app will show transaction result screens.
      If set to `False`, the third-party app will display the transaction results instead.
    - Applies to Android Till SDK, CTI, and LTI.
- **getCustomConfiguration method**:
    - Added new method `getCustomConfiguration` which retrieves the custom configuration for the requesting application.
    - Available for Android Till SDK only.

### Changed
- **TillMode parameter in transaction requests**: The `TillMode` parameter no longer needs to be included in transaction requests. It will be handled internally by Wallee to determine the appropriate integration type (e.g., `SDKApp`, `VTIApp`, `POSApp`, `WEBApp`), simplifying integration.
- The kiosk mode, previously defined via Android Till SDK manifest, will be deprecated. Integrators should migrate to the new `StartupApplication` method for managing app startup behavior.

### Deprecated
- **Deprecation of PrimaryApplication configuration**:
    - The existing `PrimaryApplication` setting in the Terminal Software Manager has been deprecated for Android Till SDK.
    - Moving forward, instead of using `PrimaryApplication`, Wallee Payment App will now save the last application that was in the foreground before starting a transaction. This application will be brought to the front once the transaction is completed.
    - For CTI/LTI, the PrimaryApplication parameter remains supported for now.
- **Deprecation of kiosk mode in Android Till SDK**: The kiosk mode previously defined in the Android Till SDK manifest is now deprecated. Integrators should use the `StartupApplication` parameter for managing app startup behavior.

### Notes
- The existing behavior involving `TillMode` and `PrimaryApplication` for CTI/LTI will continue to be supported temporarily during the transition phase to avoid disruptions. A timeline for complete deprecation will be provided.
- Developers are recommended to update their configurations to include the new `StartupApplication` and `ShowTrxResultScreens` parameters and migrate away from deprecated settings.

---


