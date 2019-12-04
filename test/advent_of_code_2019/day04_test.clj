(ns advent-of-code-2019.day04-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2019.day04 :refer [digits valid-password? valid-password-pt2?]]))

(deftest digits-test
  (is (= (digits 0) [0]))
  (is (= (digits 11) [1 1]))
  (is (= (digits 12345) [1 2 3 4 5])))

(deftest valid-password?-given-examples
  (is (= (valid-password? 111123) true))
  (is (= (valid-password? 111111) true))
  (is (= (valid-password? 112345) true))
  (is (= (valid-password? 223450) false))
  (is (= (valid-password? 123789) false)))


(deftest valid-password-pt2?-given-examples
  (is (= (valid-password-pt2? 112233) true))
  (is (= (valid-password-pt2? 123444) false))
  (is (= (valid-password-pt2? 111122) true)))
