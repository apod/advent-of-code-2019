(ns advent-of-code-2019.day03
  (:require [clojure.java.io :as io]
            [clojure.set :as set]
            [clojure.string :as str]))

(defn parse-input [s]
  (map #(str/split % #",") (str/split-lines s)))

(defn distance [[^long x1 ^long y1] [^long x2 ^long y2]]
  (+ (Math/abs (- x1 x2)) (Math/abs (- y1 y2))))

(defn command-points [[cx cy :as current-pos] command]
  (let [[dir & ns] command
        n (Integer/parseInt (str/join ns))]
    (case dir
        \R (map vector (range (inc cx) (+ cx n 1)) (repeat cy))
        \L (map vector (range (dec cx) (- cx n 1) -1) (repeat cy))
        \U (map vector (repeat cx) (range (inc cy) (+ cy n 1)))
        \D (map vector (repeat cx) (range (dec cy) (- cy n 1) -1)))))

(defn wire-points [wire-spec]
  (loop [current-pos [0 0]
         [command & rst] wire-spec
         wire-points #{}]
    (if (nil? command)
      wire-points
      (let [points (command-points current-pos command)
            current-pos (last points)]
        (recur current-pos
               rst
               (into wire-points points))))))

(defn steps-to [point wire-spec]
  (loop [current-pos [0 0]
         [command & rst] wire-spec
         steps 0]
    (if (nil? command)
      steps
      (let [points (command-points current-pos command)
            idx (.indexOf points point)
            current-pos (last points)]
        (if (not= idx -1)
          (+ steps idx 1)
          (recur current-pos
                 rst
                 (+ steps (count points))))))))


(def wire-cross-points set/intersection)

(defn closest-intersection [cross-points]
  (apply min (map #(distance [0 0] %) cross-points)))


(comment
  (let [input (parse-input (slurp (io/resource "day03/input.txt")))
        intersections (apply wire-cross-points (map wire-points input))]
    ;; First star
    #_(closest-intersection intersections)
    ;; Second star
    (apply min (map (fn [point]
                      (apply + (map #(steps-to point %) input))) intersections))
    )
  )


