(ns com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.core-test
  (:require
   [cljs.test :refer-macros [deftest testing is]]
   [com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
