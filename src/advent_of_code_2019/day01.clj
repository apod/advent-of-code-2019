(ns advent-of-code-2019.day01
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn parse-input [s]
  (pmap read-string (str/split-lines s)))

(defn sum [coll]
  (reduce + coll))

(defn fuel-required [n]
  (- (quot n 3) 2))

(defn actual-fuel-required [n]
  (sum (take-while #(<= 0 %) (drop 1 (iterate fuel-required n)))))

(comment
  (let [input (parse-input (slurp (io/resource "day01/input.txt")))]
    ;; First star
    #_(sum (pmap fuel-required input))
    ;; Second star
    (sum (pmap actual-fuel-required input))
    )
  )
