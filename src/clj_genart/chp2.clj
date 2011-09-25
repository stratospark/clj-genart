(ns clj-genart.core
  (:use [rosado.processing]
        [rosado.processing.applet]))

(def *width* 500)
(def *height* 300)
(def diam (atom 10))

(defn draw []
  "Evaluate to draw next frame."
  (framerate 24)
  (smooth)
  (background-float 180)
  (stroke-weight 4)
  (stroke-cap 0)

  ;; (if (= "a" (.keyPressed app)) (reset! diam 30))
  (doseq [h (range 10 (- *height* 15) @diam)]
    (stroke-float 0 (- 255 h))
    (line 10 h (- *width* 10) h)
    (stroke-float 255 h)
    (line 10 (+ h 4) (- *width* 10) (+ h 4))))

(defn setup []
  "Runs once."
  (smooth))

(defn key-typed [e]
  (if (= \a (.getKeyChar e))
    (reset! diam (rand-int 20))))

(defapplet app :title "Chapter 2"
  :setup setup :draw draw :size [*width* *height*]
  :key-typed key-typed)

(run app)

(stop app)
