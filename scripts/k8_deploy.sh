#!/bin/bash
set -e

# Load .env into environment
export $(grep -v '^#' .env | xargs)

# Apply all manifests with envsubst
for f in k8/*.yaml; do
  echo "Applying $f..."
  envsubst < $f | kubectl apply -f -
done
