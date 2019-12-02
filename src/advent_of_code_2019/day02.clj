(ns advent-of-code-2019.day02
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn parse-input [s]
  (read-string (str \[ s \])))

(defn run-intcode
  ([coll]
   (let [op-fn (fn [f input pos]
                 (let [end-pos (+ pos 4)
                       [p1 p2 p3] (mapv input (range (inc pos) end-pos))
                       op-res (f (get input p1) (get input p2))
                       new-input (assoc input p3 op-res)]
                   [end-pos new-input]))]
     (loop [[pos input] [0 coll]]
       (case (get input pos)
         1 (recur (op-fn + input pos))
         2 (recur (op-fn * input pos))
         99 input
         :unknown-op))))
  ([coll noun verb]
   (run-intcode (assoc coll 1 noun 2 verb))))

;; 2nd faster version
#_(defn run-intcode
  ([coll]
   (let [input (int-array coll)]
     (loop [pos 0]
       (case (aget input pos)
         1 (let [[p1 p2 p3] [(aget input (+ pos 1))
                             (aget input (+ pos 2))
                             (aget input (+ pos 3))]]
             (aset-int input p3
                       (+ (aget input p1) (aget input p2)))
             (recur (+ pos 4)))
         2 (let [[p1 p2 p3] [(aget input (+ pos 1))
                             (aget input (+ pos 2))
                             (aget input (+ pos 3))]]
             (aset-int input p3
                       (* (aget input p1) (aget input p2)))
             (recur (+ pos 4)))
         99 (vec input)
         :unknown-op))))
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
