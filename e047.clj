
; The first two consecutive numbers to have two distinct prime factors are:
; 14 = 2  7
; 15 = 3  5

; The first three consecutive numbers to have three distinct prime factors are:
; 644 = 2²  7  23
; 645 = 3  5  43
; 646 = 2  17  19.

; Find the first four consecutive integers to have four distinct primes factors. What is the first of these numbers?

(use 'clojure.contrib.profile)

(defn primes-gen [n seen]
  "Lazy generator for all the primes.
   Seen is a vector of primes we have seen so far in order. N is a current number in question."
  (prof :primes-gen
  (let [sqrt (max 3 (Math/ceil (Math/sqrt n)))
        divisors (take-while #(<= % sqrt) seen)]
    (if (some #(zero? (mod n %)) seen)
      (primes-gen (inc n) seen)
      (lazy-seq (cons n (primes-gen (inc n) (conj seen n))))))))

(def primes (primes-gen 2 []))
(assert (= (take 10 primes) [2 3 5 7 11 13 17 19 23 29]))

(defn get-prime-factors [number primes]
  "Breaks the number down to prime factors. Primes generator is provided in primes.
  This generator must be able to provide primes up to the tested value (but still it can be finite).
  Returns a vector of prime factors."
  (prof :get-prime-factors
  (loop [n number factors []]
    (if (= n 1)
      factors
      (let [sqrt (max 3 (Math/ceil (Math/sqrt n)))
            divisors (filter #(zero? (mod n %)) (take-while #(<= % sqrt) primes))
            factor (if (empty? divisors) n (first divisors))]
        (recur (/ n factor) (conj factors factor)))))))
(assert (= (get-prime-factors 11 primes) [11]))
(assert (= (get-prime-factors 16 primes) [2 2 2 2]))
(assert (= (get-prime-factors 210 primes) [2 3 5 7]))
(assert (= (get-prime-factors 248 primes) [2 2 2 31]))

(defn has-n-distinct-prime-factors? [number n primes]
  "Checks whether n has exactly four distinct prime factors.
   For example 644 has exactly three distinc prime factors. 644 = 2 x 2 x 7 x 23."
  (= (count (set (get-prime-factors number primes))) n))

(defn find-n-consecutive-ints-with-n-prime-factors [n primes]
  (loop [number 2 satisfied 0]
    (do
      (if (zero? (mod number 1000))
        (print ".")
        (flush))
    (if (has-n-distinct-prime-factors? number n primes)
      (if (= satisfied (- n 1))
        number
        (recur (inc number) (inc satisfied)))
      (recur (inc number) 0)))))
(println (find-n-consecutive-ints-with-n-prime-factors 4 primes))

