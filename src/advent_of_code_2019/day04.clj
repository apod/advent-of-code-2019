(ns advent-of-code-2019.day04)

(defn digits [n]
  (loop [n n coll '()]
    (let [r (rem n 10)
          nxt (quot n 10)]
      (if (zero? nxt)
        (conj coll r)
        (recur nxt (conj coll r))))))

(defn not-decreasing? [digits]
  (every? #(apply <= %) (partition 2 1 digits)))

(defn two-adj? [digits]
  (boolean? (some #(apply = %) (partition 2 1 digits))))

(defn exactly-two-adj? [digits]
  (boolean? (some #(= (count %) 2) (partition-by identity digits))))

(defn make-password-pred [& preds]
  (fn [n]
    ((apply every-pred preds) (digits n))))

(def valid-password?
  (make-password-pred not-decreasing? two-adj?))

(def valid-password-pt2?
  (make-password-pred not-decreasing? exactly-two-adj?))

(comment
  (let [input (range 359282 (inc 820401))]
    ;; First star
    #_(count (filter valid-password? input))
    ;; Second star
    (count (filter valid-password-pt2? input))
    )
  )

