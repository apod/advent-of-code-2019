(ns advent-of-code-2019.day01-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2019.day01 :refer [fuel-required actual-fuel-required]]))

(deftest fuel-required-given-examples
  (is (= (fuel-required 12) 2))
  (is (= (fuel-required 14) 2))
  (is (= (fuel-required 1969) 654))
  (is (= (fuel-required 100756) 33583)))

(deftest actual-fuel-required-given-examples
  (is (= (actual-fuel-required 14) 2))
  (is (= (actual-fuel-required 1969) 966))
  (is (= (actual-fuel-required 100756) 50346)))
