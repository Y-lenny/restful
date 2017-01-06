Guide to the Netty 4 Upgrade of RestExpress (v. 0.11.0)

The Netty Project made several semantical revisions to the naming conventions for its classes.  These changes where inherited by the RestExpress Project in its latest update, and will most likely cause breaking changes to most applications using RestExpress. This document is a guide to updating applications to the latest version of RestExpress.  As previously stated, the changes that effect applications using RestExpress are mainly semantical. Therefor, most (if not all) of the fixes are a simple refactorings. The largest change to the project was the organization change, which the package names of Netty classes reflect. The organization of Netty has been changed from “org.jboss.netty” to “io.netty”

org.jboss.netty.* → io.netty.*

This will be the cause of most of the import errors that applications will experience during the upgrade.  Additionally, Netty 4 has changed the naming conventions of some of their getters by excluding the “get” in the method name. This convention shift can be demonstrated with an example of the HttpResponseStatus Netty class:

httpResponseStatus.getCode() → httpResponseStatus.code()

Most “methodNotFound” errors for getters that applications will experience during the upgrade will be the result of this conventions change.  Any application that is also using either Plug-in Express (0.2.6 or earlier) or Hyper Express (2.2 or earlier) will have to upgrade to the latest version of Plug-in Express/Hyper Express. Older versions of Plug-in Express/Hyper Express are incompatible with the latest version of RestExpress. In addition, any dependency that directly references older versions of Netty (3.x.x or older) or RestExpress (0.10.5 or older) will need to be upgraded. This is due to the semantical differences between Netty 3.x.x and 4.x.x – therefor, this document can be used to upgrade those dependancies.