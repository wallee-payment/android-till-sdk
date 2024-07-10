#!/bin/bash
if [ $# -ne 2 ]; then
  echo "tag update stopped due missing script arguments";
  exit;
fi

echo "CI_COMMIT_REF_NAME = $CI_COMMIT_REF_NAME"
echo "CI_COMMIT_BRANCH = $CI_COMMIT_BRANCH"
echo "CI_PROJECT_URL = $CI_PROJECT_URL"
echo "CI_PROJECT_PATH = $CI_PROJECT_PATH"
echo "GITLAB_USER_NAME = ${GITLAB_USER_NAME}"
echo "GITLAB_USER_EMAIL = ${GITLAB_USER_EMAIL}"

COMMIT_MSG="TILL SDK VERSION -$1"

if [ "$CI_COMMIT_BRANCH" == "dev" ] || [ "$CI_COMMIT_REF_NAME" == "dev" ]; then
  echo "master branch";
  git config user.email "${GITLAB_USER_EMAIL}"
  git config user.name "${GITLAB_USER_NAME}"
  git remote set-url origin https://oauth2:$2@${CI_PROJECT_URL:8}.git
  git tag -fa -m "${COMMIT_MSG}" "$1" && \
  git push --force origin "$1" && \
  echo "tag $1 added to repository";
else
  echo "non-master branch"
fi

