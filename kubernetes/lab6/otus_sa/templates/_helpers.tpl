{{/*
Expand the name of the chart.
*/}}
{{- define "auth.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "auth.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "otus_sa.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
auth labels
*/}}
{{- define "auth.labels" -}}
helm.sh/chart: {{ include "otus_sa.chart" . }}
{{ include "auth.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
person labels
*/}}
{{- define "person.labels" -}}
helm.sh/chart: {{ include "otus_sa.chart" . }}
{{ include "person.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
order labels
*/}}
{{- define "order.labels" -}}
helm.sh/chart: {{ include "otus_sa.chart" . }}
{{ include "order.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Selector auth labels
*/}}
{{- define "auth.selectorLabels" -}}
app.kubernetes.io/name: {{.Values.auth.name}}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Selector person labels
*/}}
{{- define "person.selectorLabels" -}}
app.kubernetes.io/name: {{.Values.person.name}}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Selector order labels
*/}}
{{- define "order.selectorLabels" -}}
app.kubernetes.io/name: {{.Values.order.name}}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "auth.serviceAccountName" -}}
{{- if .Values.serviceAccount.create }}
{{- default (.Values.auth.name) .Values.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.serviceAccount.name }}
{{- end }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "person.serviceAccountName" -}}
{{- if .Values.serviceAccount.create }}
{{- default (.Values.person.name) .Values.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.serviceAccount.name }}
{{- end }}
{{- end }}

{{- define "postgresql.fullname" -}}
{{- printf .Values.postgresql.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "postgres_auth.url" -}}
{{- printf "%s%s:%s/%s?currentSchema=%s" "jdbc:postgresql://" (include "postgresql.fullname" .) .Values.postgresql.auth.port .Values.postgresql.auth.database .Values.postgresql.auth.schema | trunc 128 | trimSuffix "-" -}}
{{- end -}}
{{- define "postgres_person.url" -}}
{{- printf "%s%s:%s/%s?currentSchema=%s" "jdbc:postgresql://" (include "postgresql.fullname" .) .Values.postgresql.person.port .Values.postgresql.person.database .Values.postgresql.person.schema | trunc 128 | trimSuffix "-" -}}
{{- end -}}
{{- define "postgres_order.url" -}}
{{- printf "%s%s:%s/%s?currentSchema=%s" "jdbc:postgresql://" (include "postgresql.fullname" .) .Values.postgresql.order.port .Values.postgresql.order.database .Values.postgresql.order.schema | trunc 128 | trimSuffix "-" -}}
{{- end -}}
