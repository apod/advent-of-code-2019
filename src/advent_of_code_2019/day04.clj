(ns advent-of-code-2019.day04)

(defn digits [n]
  (loop [n n coll '()]
    (let [r (rem n 10)
          nxt (quot n 10)]
      (if (zero? nxt)
        (conj coll r)
        (recur nxt (conj coll r))))))

(defn valid-password? [n]
  (let [dgs (digits n)
        by-pair (partition 2 1 dgs)]
    (and
     (= (count dgs) 6)
     (every? #(apply <= %) by-pair)
     (boolean? (some #(apply = %) by-pair)))))

(defn valid-password-pt2? [n]
  (let [dgs (digits n)]
    (and
     (= (count dgs) 6)
     (every? #(apply <= %) (partition 2 1 dgs))
     (boolean? (some #(= (count %) 2) (partition-by identity dgs))))))

(comment
  (let [input (range 359282 (inc 820401))]
    ;; First star
    #_(count (filter valid-password? input))
    ;; Second star
    (count (filter valid-password-pt2? input))
    )
  )

