
; The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property.

; Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:

; d2d3d4=406 is divisible by 2
; d3d4d5=063 is divisible by 3
; d4d5d6=635 is divisible by 5
; d5d6d7=357 is divisible by 7
; d6d7d8=572 is divisible by 11
; d7d8d9=728 is divisible by 13
; d8d9d10=289 is divisible by 17
; Find the sum of all 0 to 9 pandigital numbers with this property.

(defn num-to-digits [number base]
  (loop [n number
         digits []]
    (if (zero? n)
      (reverse digits)
      (recur (quot n base) (conj digits (mod n base))))))
(assert (= (num-to-digits 123 10) (list 1 2 3)))
(assert (= (num-to-digits 1984 10) (list 1 9 8 4)))
(assert (= (num-to-digits 666 10) (list 6 6 6)))

(defn digits-to-num [digits base]
  (reduce #(+ (* % base) %2) 0 digits))
(assert (= (digits-to-num (list 1 2 3) 10) 123))
(assert (= (digits-to-num (list 1 9 8 4) 10) 1984))
(assert (= (digits-to-num (list 6 6 6) 10) 666))

(defn triplets []
  "Generates all triplets with unique digits"
   (for [;:let [digits (list (range 10)]
         x (range 10)
         y (range 10)
         z (range 10)
         :when (= (count (set [x y z])) 3)]
     (list x y z)))

(defn divide-by-divisibility [elems bucket-headers transformator]
  "Divides elements in given sequence by divisibility into buckets identified by bucket-headers.
  Divisibility is checked against result of application of transformator on the element.
  Any element goes to all the fitting buckets. Bucket-headers is a sequence.
  Returns map: bucket element -> vector of elements from sq."
  (loop [sq elems
         res (zipmap bucket-headers (repeat (count bucket-headers) []))]
    (if (empty? sq)
      res
      (let [fst (first sq)
            transformed (transformator fst)
            rst (rest sq)
            divisors (filter #(if (zero? (mod transformed %)) % ) bucket-headers)]
        (if (empty? divisors)
          (recur rst res)
          (recur rst (reduce #(assoc %1 %2 (conj (%1 %2) fst)) res divisors)))))))

(defn vals-sorted-by-keys [the-map]
  (for [k (sort (keys the-map))] (the-map k)))

(def buckets-descending (reverse (vals-sorted-by-keys (divide-by-divisibility (triplets) [2 3 5 7 11 13 17] #(digits-to-num % 10)))))

(defn combine-pandigital [buckets acc-list]
  "Creates pandigital vectors that are combinations of subvectors from different buckets according to the problem statement.
  Every pandigital vector is a concatenation of a triplet from each bucket. Buckets are descending (starting with 17 and ending with 2)."
  (let [combine-parts
        (fn [triplet previous]
          (assert (= (count triplet) 3))
          (assert (or (empty? previous) (>= (count previous) 3)))
          (if (empty? previous)
            (apply list triplet)
            (if (and (= (nth triplet 2) (nth previous 1))
                     (= (nth triplet 1) (nth previous 0))
                     (= (.indexOf previous (nth triplet 0)) -1))
              (conj previous (nth triplet 0))
              nil)))]
  (if (empty? buckets)
    [acc-list]
    (apply concat (for [triplet (first buckets)
                        :let [combined (combine-parts triplet acc-list)]
                        :when combined]
                    (do
                      (combine-pandigital (rest buckets) combined)))))))

; these are not pandigitals yet as they are missing the first digit
(def almost-pandigitals-digits (combine-pandigital buckets-descending (list)))

(defn add-first-digit [almost-pandigital-digits]
  "Finds missing first digit and adds it to the beginning."
  (let [digit-set (set (range 10))
        diff-set (clojure.set/difference digit-set (set almost-pandigital-digits))]
    (assert (= (count diff-set) 1))
    (conj almost-pandigital-digits (first diff-set))))

; correct pandigitals digits except for ones starting with 0 
(def pandigitals-digits (map add-first-digit almost-pandigitals-digits))

; remove ones starting with 0 and combine digits to numbers
(def result-pandigitals (map #(digits-to-num % 10) (filter #(not (zero? (nth % 0))) pandigitals-digits)))
(println result-pandigitals)
(println (reduce + result-pandigitals))

