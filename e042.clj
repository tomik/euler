
;The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:

;1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

;By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle number then we shall call the word a triangle word.

;Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common English words, how many are triangle words?


; for some strange reason working directory is ./data already
(def Fm_WORDS "42.txt")

; set of first 100 triangle numbers
(def triangles (set (map #(* (/ 1 2) % (+ % 1)) (range 0 100))))

(defn sum-word [word]
  "Sum alphabet positions for letters in the given word."
  (reduce  + (map #(inc (- (int %) (int \A))) word)))

; check how many words are triangular
(println (count (filter #(triangles %) (map sum-word (re-seq #"\w+" (slurp FN_WORDS))))))

