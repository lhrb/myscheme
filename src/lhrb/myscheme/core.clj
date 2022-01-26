(ns lhrb.myscheme.core
  (:refer-clojure :exclude [eval]))

;; write a simple lisp interpreter as learning exercise
;; we just use the clojure lisp reader for convenience

(def literal?
  "atomic value evaluates to itself"
  (some-fn number? string? nil? boolean?))

(defn starts-with?
  "recurring theme: we want to know if an expression begins with a specific
  symbol"
  [pred]
  (fn [form] (and (list? form) (pred (first form)))))

(defn quote?
  "not quite there yet: after the quote we expect an expression
  for example (quote? '(quote 1 2 3)) is true but also a syntax error"
  [form]
  ((starts-with? #(= 'quote %)) form))

(read-string "'(+ 1 1)")
(read-string "(quote (+ 1 1))")


(defn eval
  [env form]
  (cond
    (literal? form) form
    (quote? form) (second form)
    ((starts-with? symbol?) form) (apply (get env (first form)) (rest form))))

(get {'+ +} '+)
(eval {'+ +} '(+ 1 2))
