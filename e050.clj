
; The prime 41, can be written as the sum of six consecutive primes:
;
; 41 = 2 + 3 + 5 + 7 + 11 + 13
; This is the longest sum of consecutive primes that adds to a prime below one-hundred.
;
; The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.
; Which prime, below one-million, can be written as the sum of the most consecutive primes?
;
; We will call the consecutive primes seqs CPS. CPS which adds up to a prime will be prime CPS.

(require 'elib)

(defn calc-max-cps-length [limit]
  "Calculates length upper bound of longest cps we can encounter."
  (loop [steps 0 total 0 primes elib/primes]
    (if (> total limit)
      (- steps 1)
      (recur (inc steps) (+ total (first primes)) (rest primes)))))
; (println (map calc-max-cps-length [100 1000 10000 100000 1000000]))

(defn find-longest-prime-cps [limit primes]
  " Finds the longest prime cps and returns a following triple:
    (resulting-prime prime-cps-lenght prime-cps-first-element).
    For example for limit = 100 returns (41 6 2) because longest prime cps is 2 3 5 7 11 13
  "
  (loop [exp-length (calc-max-cps-length limit) seqs-to-consider 1]
    (let [result
           (loop [i 0]
             (let [candidate (reduce + (take exp-length (drop i primes)))]
               (cond
                 (>= i seqs-to-consider) nil
                 (elib/is-prime? candidate) (list candidate exp-length (first (drop i primes)))
                 :else (recur (inc i)))))]
      (if (not (nil? result))
        result
        ; we know that exp-length will always be > 0
        (recur (dec exp-length) (inc seqs-to-consider))
))))

(println (find-longest-prime-cps 1000000 elib/primes))
