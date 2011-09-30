(ns clj-genart.core
  (:use [rosado.processing]
        [rosado.processing.applet]))

(def *width* 500)
(def *height* 300)

(defn custom-noise [value]
  (let [count (mod value 12)]
    (pow (sin value) count)))

(defn draw-frame
  ([]
     (fn []
       (background-int 255)
       (draw-frame (rand-int 10)
                   (rand-int 10)
                   (rand-int 10)
                   (rand-int 10)
                   (/ Math/PI -2)
                   0
                   254
                   -1)))
  ([ang-noise
    radius-noise
    x-noise
    y-noise
    angle
    radius
    stroke-color
    stroke-change]
     (let [radius-noise (+ radius-noise 0.005)
           radius (+ (* 550 (noise radius-noise)) 1)

           ang-noise (+ ang-noise 0.005)
           angle (let
                     [temp-angle (+ angle (* 6 (noise ang-noise)) -3)]
                   (cond (> temp-angle 360) (- temp-angle 360)
                         (< temp-angle 0) (+ temp-angle 360)
                         :else temp-angle))

           x-noise (+ x-noise 0.01)
           y-noise (+ y-noise 0.01)

           center-x (+ (/ *width* 2) (* 100 (noise x-noise)) -50)
           center-y (+ (/ *height* 2) (* 100 (noise y-noise)) -50)

           rad (radians angle)
           x1 (+ center-x (* radius (cos rad)))
           y1 (+ center-y (* radius (sin rad)))

           oprad (+ rad Math/PI)
           x2 (+ center-x (* radius (cos oprad)))
           y2 (+ center-y (* radius (sin oprad)))

           stroke-color (+ stroke-color stroke-change)
           stroke-change (cond (> stroke-color 254) -1
                               (< stroke-color 0) 1
                               :else stroke-change)

           ]
       (stroke stroke-color 60)
       (stroke-weight 1)
       (line x1 y1 x2 y2)
       (fn []
         (draw-frame ang-noise
                     radius-noise
                     x-noise
                     y-noise
                     angle
                     radius
                     stroke-color
                     stroke-change)))))


(def next-frame (atom nil))

(defn draw []
  "Evaluate to draw next frame."
  (frame-rate 60)
  ;; (background-float 255)

  (if (nil? @next-frame) (reset! next-frame (draw-frame)))
  (reset! next-frame (@next-frame))
  ;; TODO: Find a better way to save state between frames that allows
  ;; for code updates
)

(defn setup []
  "Runs once."
  (smooth))

(defapplet app :title "Chapter 4"
  :setup setup :draw draw :size [*width* *height*])

(run app)

(stop app)
