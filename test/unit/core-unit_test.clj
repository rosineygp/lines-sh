(load-file-without-hashbang "src/test.clj")

(load-file-without-hashbang "src/includes/use.clj")
(load-file-without-hashbang "src/core.clj")

(use ["date" "git"])

(deftest "str-date-time"
  (testing "return type"
    (is (= (string? (str-date-time)) true)))

  (testing "time format (lenght)"
    (is (= (count (seq (str-date-time))) 19))))

(deftest "str-slug"
  (testing "to lower case"
    (is (= (str-slug "UPPERCASE") "uppercase")))

  (testing "replace :"
    (is (= (str-slug "c:l:e:a:n") "c-l-e-a-n")))

  (testing "replace ."
    (is (= (str-slug "c.l.e.a.n") "c-l-e-a-n")))

  (testing "replace /"
    (is (= (str-slug "c/l/e/a/n") "c-l-e-a-n")))

  (testing "replace _"
    (is (= (str-slug "c_l_e_a_n") "c-l-e-a-n")))

  (testing "replace spaces"
    (is (= (str-slug "c l e a n") "c-l-e-a-n"))))

(deftest "branch-or-tag-name"
  (testing "local branch [dev]"
    (is (= (branch-or-tag-name) "dev")))

  (testing "github actions"
    (do
      (env "GITHUB_ACTIONS" "true")
      (env "GITHUB_REF" "/refs/heads/github-actions")
      (is (= (branch-or-tag-name) "github-actions"))))

  (testing "gitlab-ci"
    (do
      (unset "GITHUB_ACTIONS")
      (unset "GITHUB_REF")
      (env "GITLAB_CI" "true")
      (env "CI_COMMIT_REF_NAME" "gitlab-ci")
      (is (= (branch-or-tag-name) "gitlab-ci"))))

  (testing "jenkins"
    (do
      (unset "GITLAB_CI")
      (unset "CI_COMMIT_REF_NAME")
      (env "JENKINS_URL" "http://jenkins")
      (env "GIT_BRANCH" "jenkins")
      (is (= (branch-or-tag-name) "jenkins"))))

  (testing "travis"
    (do
      (unset "JENKINS_URL")
      (unset "GIT_BRANCH")

      (env "TRAVIS" "true")
      (env "TRAVIS_BRANCH" "travis")
      (is (= (branch-or-tag-name) "travis"))))

  (testing "circle-ci-tag"
    (do
      (unset "TRAVIS")
      (unset "TRAVIS_BRANCH")
      (env "CIRCLECI" "true")
      (env "CIRCLE_TAG" "circle-ci-tag")
      (env "CIRCLE_BRANCH" "circle-ci-brach")
      (is (= (branch-or-tag-name) "circle-ci-tag"))))

  (testing "circle-ci-branch"
    (do
      (unset "CIRCLE_TAG")
      (is (= (branch-or-tag-name) "circle-ci-brach")))))