(ns advent-of-code-2019.day03-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2019.day03 :refer [distance wire-points wire-cross-points closest-intersection
                                               closest-by-steps]]))

(deftest distance-test
  (is (= (distance [0 0] [3 3]) 6))
  (is (= (distance [0 0] [-3 3]) 6))
  (is (= (distance [0 0] [-3 -3]) 6)))

(deftest wire-points-test
  (is (= (wire-points ["R3"])
         #{[1 0] [2 0] [3 0]}))
  (is (= (wire-points ["R3" "U2"])
         #{[1 0] [2 0] [3 0] [3 1] [3 2]}))
  (is (= (wire-points ["R3" "U2" "L2" "D2"])
         #{[1 0] [2 0] [3 0] [3 1] [3 2] [2 2] [1 2] [1 1]})))

(deftest wire-cross-points-given-examples
  (is (= (wire-cross-points (wire-points ["R8" "U5" "L5" "D3"])
                            (wire-points ["U7" "R6" "D4" "L4"]))
         #{[3 3] [6 5]})))

(deftest closest-intersection-given-examples
  (is (= (closest-intersection
          (wire-cross-points
           (wire-points ["R75" "D30" "R83" "U83" "L12" "D49" "R71" "U7" "L72"])
           (wire-points ["U62" "R66" "U55" "R34" "D71" "R55" "D58" "R83"])))
         159))
  (is (= (closest-intersection
          (wire-cross-points
           (wire-points ["R98" "U47" "R26" "D63" "R33" "U87" "L62" "D20" "R33" "U53" "R51"])
           (wire-points ["U98" "R91" "D20" "R16" "D67" "R40" "U7" "R15" "U6" "R7"])))
         135)))

(deftest closest-by-steps-given-examples
  (let [input-1 [["R75" "D30" "R83" "U83" "L12" "D49" "R71" "U7" "L72"]
                 ["U62" "R66" "U55" "R34" "D71" "R55" "D58" "R83"]]
        intersections-1 (apply wire-cross-points (map wire-points input-1))
        input-2 [["R98" "U47" "R26" "D63" "R33" "U87" "L62" "D20" "R33" "U53" "R51"]
                 ["U98" "R91" "D20" "R16" "D67" "R40" "U7" "R15" "U6" "R7"]]
        intersections-2 (apply wire-cross-points (map wire-points input-2))]
    (is (= (closest-by-steps intersections-1 input-1) 610))
    (is (= (closest-by-steps intersections-2 input-2) 410))))
