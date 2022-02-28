#!/bin/sh

docker run \
    --name mariadb \
    --rm --detach \
    --env MARIADB_DATABASE=ave \
    --env MARIADB_USER=admin \
    --env MARIADB_PASSWORD=6969 \
    --env MARIADB_ROOT_PASSWORD=6969  \
    -p 3306:3306 \
    mariadb
