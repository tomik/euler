
; The first two consecutive numbers to have two distinct prime factors are:
; 14 = 2  7
; 15 = 3  5

; The first three consecutive numbers to have three distinct prime factors are:
; 644 = 2²  7  23
; 645 = 3  5  43
; 646 = 2  17  19.

; Find the first four consecutive integers to have four distinct primes factors. What is the first of these numbers?

(require 'elib)

(defn has-n-distinct-prime-factors? [number n primes]
  "Checks whether n has exactly four distinct prime factors.
   For example 644 has exactly three distinc prime factors. 644 = 2 x 2 x 7 x 23."
  (= (count (set (elib/get-prime-factors number primes))) n))

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
(println (find-n-consecutive-ints-with-n-prime-factors 4 elib/primes))

