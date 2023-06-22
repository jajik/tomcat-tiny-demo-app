## Small Tomcat app demo

This is a small Tomcat app with two servlets which share one context. It is a demo
app so there is definitely a room for improvements but that's not the point.

### Deployment

You can compile the app by running `app-create.sh`. Then you have to move the app
into your tomcat's webapps folder (usually `/var/lib/tomcat/webapps/`).

### Demo

You can play with the scripts supplied. `user-create.sh` will add a few demo users
into your app. `blog-create.sh` will allow you to interactively create a blogpost.
