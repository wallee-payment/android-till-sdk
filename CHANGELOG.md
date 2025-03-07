# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

The SDK is following semantic versioning [Semantic Versioning 2.0.0](https://semver.org).


The key words **“MUST”, “MUST NOT”, “REQUIRED”, “SHALL”, “SHALL NOT”, “SHOULD”, “SHOULD NOT”, “RECOMMENDED”, “MAY”, and “OPTIONAL”** in this document are to be interpreted as described in [RFC 2119](https://tools.ietf.org/html/rfc2119).

## [2.0.0]

### Added
- **StartupApplication parameter**: Added new configuration parameter called `StartupApplication` in the Terminal Software Manager. This parameter replaces the existing kiosk mode configuration and manages which application is in the foreground until a transaction is initiated. After processing, the Wallee Payment App will return control to the original app.
- **ShowTrxResultScreens parameter**: Introduced `ShowTrxResultScreens` parameter to control whether transaction results are shown on the screen. The default is set to `True` (enabled), meaning Wallee will show transaction result screens. If set to `False`, the third-party app will display the transaction results instead.
- **getCustomConfiguration method**: Added new method `getCustomConfiguration` which retrieves the custom configuration for a specified application ID.

### Changed
- **TillMode parameter in transaction requests**: The `TillMode` parameter no longer needs to be included in transaction requests. It will be handled internally by Wallee to determine the appropriate integration type (e.g., `SDKApp`, `VTIApp`, `POSApp`, `WEBApp`), simplifying integration.
- The kiosk mode, previously defined via Android Till SDK manifest, will be deprecated. Integrators should migrate to the new `StartupApplication` method for managing app startup behavior.

### Deprecated
- **Deprecation of PrimaryApplication configuration**: The existing `PrimaryApplication` setting in the Terminal Software Manager has been deprecated.  Moving forward, instead of using `PrimaryApplication`, Wallee Payment App will now save the last application that was in the foreground before starting a transaction. This application will be brought to the front once the transaction is completed.
- **Deprecation of kiosk mode in Android Till SDK**: The kiosk mode previously defined in the Android Till SDK manifest is now deprecated. Integrators should use the `StartupApplication` parameter for managing app startup behavior.

### Notes
- The existing behavior involving `TillMode`, `PrimaryApplication`, and kiosk mode will continue to be supported temporarily during the transition phase to avoid disruptions. A timeline for complete deprecation will be provided.
- Developers are recommended to update their configurations to include the new `StartupApplication` and `ShowTrxResultScreens` parameters and migrate away from deprecated settings.

---




