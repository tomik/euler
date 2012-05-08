
; Often repeating functions for solving Project Euler problems.

(ns elib)

(defn number-to-digits [number]
  "For given number in a given base returns a list of its digits.
   Works only for numbers in base 10."
  (map #(- (int %) (int \0)) (seq (str number))))
(assert (= (number-to-digits 9876543210) (range 9 -1 -1)))

(defn digits-to-number [digits]
  "Inverse to number-to-digits. Implemented with Horner scheme."
  (reduce #(+ (* 10 %) %2) 0 digits))
(assert (= (digits-to-number [1 2 3]) 123))

(defn permutation-seq [coll]
  "Lazy sequence of permutations of given collection."
  (if (= (count coll) 1)
    [[(first coll)]]
    (let [cycle-coll (cycle coll)]
      (apply concat
        (for [i (range (count coll))]
          (let [shift (drop i cycle-coll)
                fst (first shift)
                rst (rest (take (count coll) shift))]
            (lazy-seq (map #(cons fst %) (permutation-seq rst)))))))))

(assert (= (doall (permutation-seq #{1 2})) [[1 2] [2 1]]))
(assert (= (doall (permutation-seq #{1 2 3})) [[1 2 3] [1 3 2] [2 3 1] [2 1 3] [3 1 2] [3 2 1]]))
(assert (= (count (doall (permutation-seq #{1 2 3 4}))) 24))

(defn is-prime? [n]
  "Check whether n is a prime."
  (if (#{2 3} n)
    true
    (if (or (< n 2) (zero? (mod n 2)))
      false
      (let [stop (Math/floor (Math/sqrt n))]
        (loop [divisor 3]
          (if (> divisor stop)
            true
            (if (zero? (mod n divisor))
              false
              (recur (inc divisor)))))))))
(assert (every? is-prime? [2 3 5 7 11 13 17 19 23 29]))
(assert (not-any? is-prime? [0 1 4 6 8 9 15 27 33]))

(defn primes-gen [n seen]
  "Lazy generator for all the primes.
   Seen is a vector of primes we have seen so far in order. N is a current number in question."
  (let [sqrt (max 3 (Math/ceil (Math/sqrt n)))
        divisors (take-while #(<= % sqrt) seen)]
    (if (some #(zero? (mod n %)) seen)
      (primes-gen (inc n) seen)
      (lazy-seq (cons n (primes-gen (inc n) (conj seen n)))))))

; lazy sequence of all primes
(def primes (primes-gen 2 []))
(assert (= (take 10 primes) [2 3 5 7 11 13 17 19 23 29]))
(assert (every? is-prime? (take 1000 primes)))

(defn get-prime-factors [number primes]
  "Breaks the number down to prime factors. Primes generator is provided in primes.
  This generator must be able to provide primes up to the tested value (but still it can be finite).
  Returns a vector of prime factors."
  (loop [n number factors []]
    (if (= n 1)
      factors
      (let [sqrt (max 3 (Math/ceil (Math/sqrt n)))
            divisors (filter #(zero? (mod n %)) (take-while #(<= % sqrt) primes))
            factor (if (empty? divisors) n (first divisors))]
        (recur (/ n factor) (conj factors factor))))))
(assert (= (get-prime-factors 11 primes) [11]))
(assert (= (get-prime-factors 16 primes) [2 2 2 2]))
(assert (= (get-prime-factors 210 primes) [2 3 5 7]))
(assert (= (get-prime-factors 248 primes) [2 2 2 31]))

