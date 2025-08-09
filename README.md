

````markdown
# 🏠 LandlordApp


A modern Android application designed to help landlords efficiently manage their rental properties and tenants. Built with **MVVM architecture** and modern Android development practices.

## ✨ Features

- **📋 Tenant Management**: Add, view, and manage tenant information
- **🏢 Property Dashboard**: Overview of rental properties and occupancy
- **📱 Modern UI**: Clean, intuitive interface with Material Design
- **🔄 Real-time Updates**: Live data binding for instant UI updates
- **📊 Tenant Counter**: Track the number of tenants across properties
- **🎨 Responsive Design**: Optimized for various screen sizes

## 🏗️ Architecture

This app follows the **MVVM (Model-View-ViewModel)** architectural pattern with:

- **ViewModel**: Manages UI-related data and business logic
- **LiveData & MutableLiveData**: Reactive data observation
- **Data Binding**: Declarative UI updates
- **Navigation Drawer**: Intuitive app navigation
- **Lifecycle-aware Components**: Proper handling of Android lifecycle

## 📱 Screenshots

| Main Dashboard | Add Tenant | Navigation Drawer |
|:---:|:---:|:---:|
| *Dashboard view with property overview* | *Add new tenant form* | *App navigation menu* |

## 🚀 Getting Started

### Prerequisites

- **Android Studio**: Arctic Fox (2020.3.1) or newer
- **Android SDK**: API level 24 (Android 7.0) or higher
- **Kotlin**: 1.8.0 or newer
- **Gradle**: 7.0 or newer

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/mskmumo/LandlordApp.git
   cd LandlordApp
````

2. **Open in Android Studio**

   * Launch Android Studio
   * Select "Open an Existing Project"
   * Navigate to the cloned directory and select it

3. **Sync Project**

   * Wait for Gradle sync to complete
   * Resolve any dependency issues if prompted

4. **Run the App**

   * Connect an Android device or start an emulator
   * Click the "Run" button (▶️) or press `Shift + F10`

## 🛠️ Technical Implementation

### Core Components

#### 1. **TenantViewModel**

* Manages tenant data using `MutableLiveData<String>`
* Implements reactive data transformations with `Transformations.map()`
* Provides live tenant counter functionality
* Handles business logic separation from UI

```kotlin
class TenantViewModel : ViewModel() {
    private val _tenantInfo = MutableLiveData<String>("")
    val tenantInfo: LiveData<String> = _tenantInfo
    
    val capitalizedTenantInfo: LiveData<String> = Transformations.map(tenantInfo) {
        it.uppercase()
    }
    
    private val _tenantCount = MutableLiveData<Int>(0)
    val tenantCount: LiveData<Int> = _tenantCount
}
```

#### 2. **Data Binding Integration**

* XML layouts wrapped in `<layout>` tags
* Direct binding between UI components and ViewModel
* Automatic UI updates when LiveData changes

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android">
    <data>
        <variable
            name="viewModel"
            type="com.example.navigationuidemo2.TenantViewModel" />
    </data>
    
    <TextView
        android:text='@{viewModel.capitalizedTenantInfo}'
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
</layout>
```

#### 3. **Activity Architecture**

* Uses `by viewModels()` delegate for ViewModel initialization
* Implements `DataBindingUtil` for layout binding
* Sets `lifecycleOwner` for automatic LiveData observation

## 📦 Dependencies

### Core Android Libraries

```kotlin
dependencies {
    // Core Android
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    
    // Architecture Components
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.activity:activity-ktx:1.8.2")
    
    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
```

## 📁 Project Structure

```
LandlordApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/navigationuidemo2/
│   │   │   │   ├── MainActivity.kt          # Main dashboard activity
│   │   │   │   ├── AddTenant.kt            # Add tenant functionality
│   │   │   │   └── TenantViewModel.kt      # ViewModel for tenant data
│   │   │   ├── res/
│   │   │   │   ├── layout/                 # XML layout files
│   │   │   │   ├── values/                 # Colors, strings, themes
│   │   │   │   └── drawable/               # App icons and images
│   │   │   └── AndroidManifest.xml         # App configuration
│   │   ├── test/                           # Unit tests
│   │   └── androidTest/                    # Instrumentation tests
│   ├── build.gradle.kts                    # App-level build configuration
│   └── proguard-rules.pro                  # ProGuard configuration
├── gradle/                                 # Gradle wrapper files
├── build.gradle.kts                        # Project-level build configuration
└── README.md                               # This file
```

## 🎯 Key Features Explained

### **Reactive Tenant Counter**

The app includes a live tenant counter that updates automatically:

```kotlin
private val _tenantCount = MutableLiveData<Int>(0)
val tenantCount: LiveData<Int> = _tenantCount

fun addTenant(name: String, unit: String, rent: String) {
    // Add tenant logic
    _tenantCount.value = (_tenantCount.value ?: 0) + 1
}
```

The UI updates automatically without manual refresh:

```xml
android:text='@{"Total Tenants: " + viewModel.tenantCount}'
```

## How to Install & Run

### Requirements

* Android Studio (Arctic Fox or newer)
* Android Emulator or Android phone
* Android SDK 29+

### Steps

```bash
git clone https://github.com/mskmumo/LandlordApp.git
cd LandlordApp
```

1. Open the folder in **Android Studio**
2. Let **Gradle Sync** complete
3. Click **Run** (select your emulator or device)
4. Test the **Add Tenant** functionality

### Expected Behavior

* Enter tenant name, unit, and rent → Tap Add
* See details in UPPERCASE
* See live tenant count
* Rotate screen → data remains

## GitHub Repository

> [https://github.com/mskmumo/LandlordApp](https://github.com/mskmumo/LandlordApp)

## 🧪 Testing

### Running Tests

```bash
# Run unit tests
./gradlew test

# Run instrumentation tests
./gradlew connectedAndroidTest

# Run all tests
./gradlew check
```

### Test Coverage

* **Unit Tests**: ViewModel logic and data transformations
* **UI Tests**: User interaction flows and data binding
* **Integration Tests**: End-to-end tenant management workflows

## 🔧 Development

### Building from Source

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Install on connected device
./gradlew installDebug
```

### Code Style

This project follows [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html) and uses:

* **ktlint** for code formatting
* **detekt** for static code analysis
* **Android Architecture Guidelines**

## 🤝 Contributing

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### Development Guidelines

* Follow MVVM architecture patterns
* Write unit tests for new features
* Update documentation for significant changes
* Use meaningful commit messages

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Celestine Maria** - [@mskmumo](https://github.com/mskmumo)

## 🙏 Acknowledgments

* Android Architecture Components team
* Material Design guidelines
* Kotlin community for best practices

## 📚 Learn More

* [Android Architecture Guide](https://developer.android.com/guide/components/activities/activity-lifecycle)
* [MVVM Pattern](https://developer.android.com/jetpack/guide)
* [LiveData Documentation](https://developer.android.com/topic/libraries/architecture/livedata)
* [Data Binding Guide](https://developer.android.com/topic/libraries/data-binding)

```

---

This file is now free of YAML syntax errors and ready to use on GitHub or your documentation system.

If you want me to help with anything else, just ask!
```
