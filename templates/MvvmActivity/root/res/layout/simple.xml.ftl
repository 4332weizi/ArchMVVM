<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context="${packageName}.${activityClass}">

    <data>
        <variable
            name="viewModel"
            type="${packageName}.${viewModelName}" />
    </data>

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <#if isNewProject!false>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_centerInParent="true"
                android:text="Hello World!" />
        </#if>

    </RelativeLayout>
</layout>
