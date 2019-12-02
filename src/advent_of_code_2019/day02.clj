(ns advent-of-code-2019.day02
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn parse-input [s]
  (read-string (str \[ s \])))

(defn run-intcode
  ([coll]
   (let [input (int-array coll)]
     (loop [pos 0]
       (let [op (aget input pos)]
         (case op
           1 (let [[p1 p2 p3] [(aget input (+ pos 1))
                               (aget input (+ pos 2))
                               (aget input (+ pos 3))]]
               (aset input p3
                     (+ (aget input p1) (aget input p2)))
               (recur (+ pos 4)))
           2 (let [[p1 p2 p3] [(aget input (+ pos 1))
                               (aget input (+ pos 2))
                               (aget input (+ pos 3))]]
               (aset input p3
                     (* (aget input p1) (aget input p2)))
               (recur (+ pos 4)))
           99 (vec input)
           :unknown-op)))))
  ([coll noun verb]
   (run-intcode (assoc coll 1 noun 2 verb))))

(defn noun-and-verb-for-output [input desired-output]
  (loop [noun 0 verb 0]
    (let [res (first (run-intcode input noun verb))]
      (cond
        (= res desired-output) [noun verb]
        (> noun 99) :not-found
        (> verb 99) (recur (inc noun) 0)
        :else (recur noun (inc verb))))))

(comment
  (time
   (let [input (parse-input (slurp (io/resource "day02/input.txt")))]
     ;; First star
     #_(first (run-intcode (assoc input
                                1 12
                                2 2)))
     ;; Second star
     (let [[noun verb] (noun-and-verb-for-output input 19690720)]
       (+ (* 100 noun) verb))))
  )
