# tag creation date in format YYYY-MM-DD
ANCHOR_DATE=$(git log -1 --simplify-by-decoration --pretty="format:%as" j-antplus-$1)
# anchor is the version number without dots
ANCHOR_VERSION=$(echo "$1" | sed "s/\.//g")
# the release notes in markdown format
NOTES=$(cat <<-END
# j-antplus-$1

- [Changelog](https://github.com/heyteacher/j-antplus/blob/master/CHANGELOG.md#j-antplus-$ANCHOR_VERSION---$ANCHOR_DATE)

- [Commits](https://github.com/heyteacher/j-antplus/commits/j-antplus-$1)

- [Maven Central Repository](https://central.sonatype.com/artifact/io.github.heyteacher/j-antplus/$1)

## Installing

- Gradle

  \`\`\`kotlin
  implementation("io.github.heyteacher:j-antplus:$1")
  \`\`\`

- Maven

  \`\`\`xml
  <dependency>
      <groupId>io.github.heyteacher</groupId>
      <artifactId>j-antplus</artifactId>
      <version>$1</version>
  </dependency>
  \`\`\`
END

)
# create the release
gh release create -R heyteacher/j-antplus j-antplus-$1 --notes "$NOTES"