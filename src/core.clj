(defn str-date-time []
    (nth (date ["+'%Y-%m-%d %T'"]) 0))
  
  (defn str-slug [string]
    (str-lower-case (reduce
                     (fn [a b] (str-replace a b "-"))
                     string [":" "." "/" "_" " "])))
  
  (defn output-line-action [action]
    (println-stderr (green action)))
  
  (defn output-line-banner [name]
    (println-stderr (str (bg-blue (bold (white (str  name " > " (str-date-time))))))))
  
  (defn branch-or-tag-name []
    (cond
      (= (env "GITHUB_ACTIONS") "true") (last (str-split (env "GITHUB_REF") "/"))
      (= (env "GITLAB_CI") "true") (env "CI_COMMIT_REF_NAME")
      (= (string? (env "JENKINS_URL")) true) (env "GIT_BRANCH")
      (= (env "TRAVIS") "true") (env "TRAVIS_BRANCH")
      (= (env "CIRCLECI") "true") (if (string? (env "CIRCLE_TAG")) (env "CIRCLE_TAG") (env "CIRCLE_BRANCH"))
      (= (nth (git ["rev-parse"
                    "--is-inside-work-tree"]) 0) "true") (nth (git ["rev-parse"
                                                                    "--abbrev-ref"
                                                                    "HEAD"]) 0)))
  
  (defn print-command [result]
    (let [std (nth result 0)
          err (nth result 1)
          exit-code (nth result 2)]
      (if (= (empty? std) false) (println std))
      (if (= (empty? err) false) (println-stderr (red err)))
      (if (number? exit-code) (println-stderr (magenta exit-code)))))
