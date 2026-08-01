# Add project specific ProGuard rules here.
# By default, the flags in this file are appended after configuration specified
# in F:\Android SDK\tools\proguard\proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.kts.

# Keep Hilt classes
-keep class * extends android.app.Application
-keep class * extends android.app.Activity
-keep class * extends android.app.Service
-keep class * extends android.content.BroadcastReceiver
-keep class * extends android.content.ContentProvider
-keep class * extends android.app.backup.BackupAgentHelper
-keep class * extends android.preference.Preference
-keep class com.google.dagger.hilt.android.internal.managers.** { *; }
-keep class com.google.dagger.hilt.android.internal.lifecycle.** { *; }

# Kotlinx Serialization if used later
-keepattributes *Annotation*, EnclosingMethod, InnerClasses, Signature
-keepnames class kotlinx.serialization.internal.GeneratedSerializer { *; }
-keepclassmembers class * {
    *** Companion;
    *** $serializer;
}
