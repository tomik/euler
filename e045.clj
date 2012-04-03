; Triangle, pentagonal, and hexagonal numbers are generated by the following formulae:
;
; Triangle    Tn=n(n+1)/2   1, 3, 6, 10, 15, ...
; Pentagonal    Pn=n(3n - 1)/2   1, 5, 12, 22, 35, ...
; Hexagonal   Hn=n(2n - 1)   1, 6, 15, 28, 45, ...
; It can be verified that T285 = P165 = H143 = 40755.
;
; Find the next triangle number that is also pentagonal and hexagonal.


; Naive search solution
;
; Keeps enumerating numbers until they are all equal.
; In every step enumerates the smallest one from tri/pen/hex sequence.

(defn gen-triangle-number [n]
  "Generates triangle number for given n."
  (/ (* n (+ n 1)) 2))

(defn gen-pentagonal-number [n]
  "Generates pentagonal number for given n."
  (/ (* n (- (* 3 n) 1)) 2))

(defn gen-hexagonal-number [n]
  "Generates hexagonal number for given n."
  (* n (- (* 2 n) 1)))

(defn- do-tri-pen-hex-seq [data]
  (lazy-seq
    (let [min-record-key (apply min-key #((data %1) :last) (keys data))
          min-record (data min-record-key)
          new-number ((min-record :gen) (+ 1 (min-record :index)))
          new-record (assoc min-record :last new-number :index (inc (min-record :index)))
          new-data (assoc data min-record-key new-record)]
      (if (every? #(= ((new-data %) :last) new-number) (keys new-data))
        (cons new-number (tri-pen-hex-seq-on-data new-data))
        (tri-pen-hex-seq-on-data new-data)))))

; Sequence of numbers that are triangle, pentagonal and hexagonal at the same time.
(def tri-pen-hex-seq
    (tri-pen-hex-seq-on-data {:triangle {:last 0 :index 1 :gen gen-triangle-number}
                              :pentagonal {:last 0 :index 1 :gen gen-pentagonal-number}
                              :hexagonal {:last 0 :index 1 :gen gen-hexagonal-number}}))

(println (take 2 tri-pen-hex-seq))
