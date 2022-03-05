package com.setesh.commons.binding.binders

/**
 * This interface provides extension functions that help bind views to observable streams.
 * An example of usage would be: `myView.bindIsEnabled(myViewModel.isEnabled)`
 *
 * It is meant to be used in Fragments, but it can also be used in custom views if a
 * LifecycleOwner is provided to the view.
 *
 * It is an aggregation of other interfaces to split utilities by concrete receivers (views).
 */
interface DataBinder: ListBinders, TextViewBinders