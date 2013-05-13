
# wrap-verbs

[Ring](https://github.com/ring-clojure/ring) middleware to convert
the *_method* parameter to the HTTP verb for the request.  Allows
HTML forms to send all HTTP request methods.

## Usage

Available from [Clojars](https://clojars.org/wrap-verbs)

```clojure
(ns my.namespace
  (:use [ring.middleware.verbs :only [wrap-verbs]]))

(def app
  (-> #'app-routes
    (wrap-verbs)
    (handler/site)))
```

Which allows the following HTML form to send a _DELETE_ request.

```html
<form method="post" action="/some/action">
  <input type="hidden" name="_method" value="delete" />
  <input type="submit" value="Delete Something" />
</form>
```

