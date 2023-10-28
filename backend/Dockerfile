FROM node:18-bullseye-slim as build

ARG LOG_FILE

ARG TARGETARCH

RUN rm /bin/sh && ln -s /bin/bash /bin/sh

RUN npm i npm@latest -g

RUN mkdir /app

WORKDIR /app

COPY package*.json yarn*.lock ./

RUN npm ci && npm cache clean --force

COPY . .

RUN npm run build

FROM node:18-bullseye-slim as base

RUN apt-get update \
    && apt-get install -y --no-install-recommends tini \
    && rm -rf /var/lib/apt/lists/*

ENTRYPOINT ["/usr/bin/tini", "--"]

RUN mkdir /app && chown -R node:node /app

WORKDIR /app

USER node

COPY --from=build --chown=node:node /app .

HEALTHCHECK --interval=30s CMD node healthcheck.js

RUN mkdir "logs" && touch "logs/$LOG_FILE.log"

FROM base as production

CMD [ "node", "./dist/main.js" ]
