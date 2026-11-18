
git-cliff -c tool/cliff.toml -t $1 | sed "s|<REPO>|$2|g" > CHANGELOG.md