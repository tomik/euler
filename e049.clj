
; The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.

; There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.
; What 12-digit number do you form by concatenating the three terms in this sequence?

; We will call this sequence a "prime-perm-aseq" (Prime Permutation Arithmetic Sequence)

(require 'elib)

(defn get-prime-perm-aseq [number]
  "Checks whether number starts any prime-perm-aseq.
   If so returns this one as a vector, otherwise returns nil."
  (let [perm-set (set
                   (filter #(and (> % number) (elib/is-prime? %))
                     (map elib/digits-to-number (elib/permutation-seq (elib/number-to-digits number)))))
        perm-seq (sort perm-set)
        fst number]
    ; loop through potential second elements in the sequence
    (loop [sq perm-seq]
      (if (empty? sq)
        nil
        (let [snd (first sq)
              thd (+ snd (- snd fst))]
          (if (contains? perm-set thd)
            (list fst snd thd)
            (recur (rest sq))))))))

(defn find-prime-perm-aseqs [interval]
  "Finds all prime-perm-aseqs in given interval."
   (filter #(not (nil? %)) (map get-prime-perm-aseq (filter elib/is-prime? interval))))

(println (find-prime-perm-aseqs (range 1000 10000)))

