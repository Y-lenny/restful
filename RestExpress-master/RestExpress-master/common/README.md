[![Build Status](https://buildhive.cloudbees.com/job/RestExpress/job/RestExpress-Common/badge/icon)](https://buildhive.cloudbees.com/job/RestExpress/job/RestExpress-Common/)

RestExpress-Common
==================

Utility classes and common functionality across RestExpress projects.

Maven Usage:
------------
Stable build:
```xml
<dependency>
	<groupId>com.strategicgains</groupId>
	<artifactId>RestExpress-Common</artifactId>
	<version>0.10.3</version>
</dependency>
```
Development (snapshot) build:
```xml
<dependency>
	<groupId>com.strategicgains</groupId>
	<artifactId>RestExpress-Common</artifactId>
	<version>0.10.4-SNAPSHOT</version>
</dependency>
```

Release Notes
=============
Versions now track with RestExpress.  See parent README.

1.0.3 - In development
-----
* Added Callback<T> interface.

1.0.2 - May 31, 2013
--------------------
* Made QueryRange Cloneable and added copy constructor, as well as unit tests (copied from RestExpress).

1.0.1 - March 4, 2013
---------------------
* Optimized QueryOrder.addSort(Strings…) to use strings.length when creating underlying list.
* Added FilterOperator, changing QueryFilter constructor to require a List<FilterComponent> instead of Map<String, String>.

1.0.0 - January 11, 2013
------------------------
* Extracted Query-related objects and StringUtils from RestExpress proper.
* Repackaged Query-related classes to facilitate extension in RestExpress proper without changing how clients use the factory capabilities.
