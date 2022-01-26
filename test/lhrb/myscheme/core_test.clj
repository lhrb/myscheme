(ns lhrb.myscheme.core-test
  (:require [lhrb.myscheme.core :refer [literal? quote? eval]]
            [clojure.test :refer :all]))

(deftest literal
  (is (literal? (read-string "42")))
  (is (literal? (read-string "nil")))
  (is (literal? (read-string "\"string\"")))
  (is (literal? (read-string "true")))
  (is (literal? (read-string "false"))))

(deftest quote.
  (is (quote? '(quote (+ 1 1))))
  (is (not (quote? 'quote)))
  (is (not (quote? "str"))))

(deftest eval.
  (is (= 42 (eval {} 42)))
  (is (= '(+ 1 1) (eval {} '(quote (+ 1 1))))))
