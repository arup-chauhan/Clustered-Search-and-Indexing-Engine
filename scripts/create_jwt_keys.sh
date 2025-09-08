#!/usr/bin/env bash
set -euo pipefail
mkdir -p src/main/resources/keys
openssl rand -base64 48 | tr -d '\n' > /tmp/jwt.secret
echo "Wrote base64 secret to /tmp/jwt.secret; put it in JWT_HMAC_BASE64"