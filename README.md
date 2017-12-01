# dropwizard-csrf-example

This project has a filter and a base view class that implements a basic csrf protection on POST forms for dropwizard.

### CSRF feature

1. On first request (GET), `CsrfFilter` generates a new csrf token stored as a session attribute.
   The lifetime of the token equals to the lifetime of session.
2. Generated forms should contain this CSRF token, therefore we have to pass this parameter to our `View`s.
   `CsrfView` is a base view that puts csrf token from session to the view.
3. View template (freemarker or mustache) can include this token in a hidden form field:
   `<input type="hidden" name="csrf_token" value="{{csrfToken}}"/>`
4. Request comes to `CsrfFilter` as a POST. Now it checks whether the form parameter `csrf_token` actually contains
   the token in the session. If not, it gives an unauthorized error.

### mustache templates

This project successfully demonstrates how one can use and import mustache templates with parameters.
Login page `/login` is written in a composable way, distributed into 4 convenient template files:

- `header.mustache`: contains html header, `title` is a template variable that can be specified on every page
- `footer.mustache`: contains the footer
- `response_message.mustache`:  contains bootstrap alert styles for various type of messages
- `login.mustache`: login page, used by `LoginView` class

### flash scope

This project also makes use of `Flash` from dropwizard to display some informational messages that is only meant to be
consumed and seen once. In this example, it shows the parameters supplied to login form.

`@Session Flash<String> message`


How to start the application
---

1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/*.jar server config.yml`
3. To check that your application is running enter url [`http://localhost:8080/login`](http://localhost:8080/login)

