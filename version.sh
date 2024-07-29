#!/bin/bash
if [ $# -ne 2 ]; then
  echo "tag update stopped due missing script arguments";
  exit;
fi

COMMIT_MSG="TILL SDK VERSION -$2"
echo "creating version tag for "$1" branch"
git config user.email "${GITLAB_USER_EMAIL}"
git config user.name "${GITLAB_USER_NAME}"
git remote set-url origin https://oauth2:"$PROJECT_ACCESS_TOKEN"@"${CI_PROJECT_URL:8}".git && \
git tag -fa -m "${COMMIT_MSG}" "$2" && \
git push --force origin "$2" && \
echo "tag $2 added to repository";
