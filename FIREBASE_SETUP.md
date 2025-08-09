# Firebase Setup Guide for LandlordApp

## Prerequisites
1. Create a Firebase project at [Firebase Console](https://console.firebase.google.com/)
2. Enable Firestore Database
3. Enable Authentication (optional for user management)

## Configuration Steps

### 1. Add Firebase to Android Project
```bash
# Install Firebase CLI
npm install -g firebase-tools

# Login to Firebase
firebase login

# Add Firebase to your Android app
firebase init
```

### 2. Download google-services.json
1. Go to Firebase Console → Project Settings
2. Click "Add app" → Android
3. Register your app with package name: `com.example.navigationuidemo2`
4. Download `google-services.json` and place it in `app/` directory

### 3. Update Application Class
Create a custom Application class to initialize Firebase:

```kotlin
package com.example.navigationuidemo2

import android.app.Application
import com.google.firebase.FirebaseApp

class LandlordApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
```

### 4. Update AndroidManifest.xml
Add the application class to manifest:
```xml
<application
    android:name=".LandlordApplication"
    ...>
</application>
```

### 5. Firestore Database Rules
Set up Firestore rules for security:

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /tenants/{tenantId} {
      allow read: if request.auth != null;
      allow create: if request.auth != null;
      allow update: if request.auth != null;
      allow delete: if request.auth != null;
    }
  }
}
```

### 6. Test Firebase Connection
Run the app and verify:
- [ ] Firebase initialization successful
- [ ] Firestore database accessible
- [ ] Real-time updates working
- [ ] Data persistence across app restarts

## Troubleshooting
- Ensure `google-services.json` is in the correct location
- Check Firebase project settings for correct package name
- Verify internet connectivity for Firestore access
- Check Firebase console for any security rules issues
