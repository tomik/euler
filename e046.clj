
; It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.
;
; 9 = 7 + 212
; 15 = 7 + 222
; 21 = 3 + 232
; 25 = 7 + 232
; 27 = 19 + 222
; 33 = 31 + 212
;
; It turns out that the conjecture was false.
; What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?

(require 'elib)

(defn gen-odd-composites []
  "Generates odd composite numbers starting with 1."
  (filter #(not (elib/is-prime? %)) (iterate #(+ % 2) 3)))
(assert (= [9 15 21 25 27 33 35 39 45 49] (take 10 (gen-odd-composites))))

(defn check-goldbach? [n]
  "Checks whether n (must be odd composite) follows Goldbach conjencture."
  (some #(elib/is-prime? (- n %)) (map #(* 2 % %) (range 1 n))))
(assert (every? check-goldbach? (take 10 (gen-odd-composites))))

(println (first (take 1 (filter #(not (check-goldbach? %)) (gen-odd-composites)))))


