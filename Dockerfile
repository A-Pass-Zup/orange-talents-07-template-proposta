ARG SERVICO_EXTERNO_ANALISE_SOLICITACAO_URI
ARG SERVICO_EXTERNO_CONTAS_BASE_URI
ARG SERVICO_EXTERNO_CONTAS_CARTOES_URN
ARG SERVICO_EXTERNO_CONTAS_CARTOES_BLOQUEIO_URN
ARG SERVICO_EXTERNO_CONTAS_CARTOES_AVISO_URN
ARG SERVICO_EXTERNO_CONTAS_CARTOES_CARTEIRA_DIGITAL_URN
ARG AUTHORIZATION_SERVER_ISSUER_URI
ARG AUTHORIZATION_SERVER_JWK_SET_URI
ARG METRICS_TAGS_APLICACAO
ARG METRICS_TAGS_AMBIENTE
ARG JAEGER_HTTP_SENDER_URL
ARG JAEGER_PROB_SAMPLING_RATE

########################################################################################################################
FROM maven:3-openjdk-11 AS builder

ARG SERVICO_EXTERNO_ANALISE_SOLICITACAO_URI
ARG SERVICO_EXTERNO_CONTAS_BASE_URI
ARG SERVICO_EXTERNO_CONTAS_CARTOES_URN
ARG SERVICO_EXTERNO_CONTAS_CARTOES_BLOQUEIO_URN
ARG SERVICO_EXTERNO_CONTAS_CARTOES_AVISO_URN
ARG SERVICO_EXTERNO_CONTAS_CARTOES_CARTEIRA_DIGITAL_URN
ARG AUTHORIZATION_SERVER_ISSUER_URI
ARG AUTHORIZATION_SERVER_JWK_SET_URI
ARG METRICS_TAGS_APLICACAO
ARG METRICS_TAGS_AMBIENTE
ARG JAEGER_HTTP_SENDER_URL
ARG JAEGER_PROB_SAMPLING_RATE

ENV DATABASE_PLATFORM h2
ENV DATABASE_DRIVER org.h2.Driver
ENV DATABASE_URL jdbc:h2:mem:testdb
ENV DATABASE_USER root
ENV DATABASE_PASSWORD 12345
ENV SERVICO_EXTERNO_ANALISE_SOLICITACAO_URI $SERVICO_EXTERNO_ANALISE_SOLICITACAO_URI
ENV SERVICO_EXTERNO_CONTAS_BASE_URI $SERVICO_EXTERNO_CONTAS_BASE_URI
ENV SERVICO_EXTERNO_CONTAS_CARTOES_URN $SERVICO_EXTERNO_CONTAS_CARTOES_URN
ENV SERVICO_EXTERNO_CONTAS_CARTOES_BLOQUEIO_URN $SERVICO_EXTERNO_CONTAS_CARTOES_BLOQUEIO_URN
ENV SERVICO_EXTERNO_CONTAS_CARTOES_AVISO_URN $SERVICO_EXTERNO_CONTAS_CARTOES_AVISO_URN
ENV SERVICO_EXTERNO_CONTAS_CARTOES_CARTEIRA_DIGITAL_URN $SERVICO_EXTERNO_CONTAS_CARTOES_CARTEIRA_DIGITAL_URN
ENV AUTHORIZATION_SERVER_ISSUER_URI $AUTHORIZATION_SERVER_ISSUER_URI
ENV AUTHORIZATION_SERVER_JWK_SET_URI $AUTHORIZATION_SERVER_JWK_SET_URI
ENV METRICS_TAGS_APLICACAO $METRICS_TAGS_APLICACAO
ENV METRICS_TAGS_AMBIENTE $METRICS_TAGS_APLICACAO
ENV JAEGER_HTTP_SENDER_URL $JAEGER_HTTP_SENDER_URL
ENV JAEGER_PROB_SAMPLING_RATE $JAEGER_PROB_SAMPLING_RATE

COPY ./src/ app/src/
COPY ./pom.xml app/
RUN /usr/bin/mvn -f app/ clean package

########################################################################################################################
FROM openjdk:11

ARG SERVICO_EXTERNO_ANALISE_SOLICITACAO_URI
ARG SERVICO_EXTERNO_CONTAS_BASE_URI
ARG SERVICO_EXTERNO_CONTAS_CARTOES_URN
ARG SERVICO_EXTERNO_CONTAS_CARTOES_BLOQUEIO_URN
ARG SERVICO_EXTERNO_CONTAS_CARTOES_AVISO_URN
ARG SERVICO_EXTERNO_CONTAS_CARTOES_CARTEIRA_DIGITAL_URN
ARG AUTHORIZATION_SERVER_ISSUER_URI
ARG AUTHORIZATION_SERVER_JWK_SET_URI
ARG METRICS_TAGS_APLICACAO
ARG METRICS_TAGS_AMBIENTE
ARG JAEGER_HTTP_SENDER_URL
ARG JAEGER_PROB_SAMPLING_RATE

ENV SERVICO_EXTERNO_ANALISE_SOLICITACAO_URI $SERVICO_EXTERNO_ANALISE_SOLICITACAO_URI
ENV SERVICO_EXTERNO_CONTAS_BASE_URI $SERVICO_EXTERNO_CONTAS_BASE_URI
ENV SERVICO_EXTERNO_CONTAS_CARTOES_URN $SERVICO_EXTERNO_CONTAS_CARTOES_URN
ENV SERVICO_EXTERNO_CONTAS_CARTOES_BLOQUEIO_URN $SERVICO_EXTERNO_CONTAS_CARTOES_BLOQUEIO_URN
ENV SERVICO_EXTERNO_CONTAS_CARTOES_AVISO_URN $SERVICO_EXTERNO_CONTAS_CARTOES_AVISO_URN
ENV SERVICO_EXTERNO_CONTAS_CARTOES_CARTEIRA_DIGITAL_URN $SERVICO_EXTERNO_CONTAS_CARTOES_CARTEIRA_DIGITAL_URN
ENV AUTHORIZATION_SERVER_ISSUER_URI $AUTHORIZATION_SERVER_ISSUER_URI
ENV AUTHORIZATION_SERVER_JWK_SET_URI $AUTHORIZATION_SERVER_JWK_SET_URI
ENV METRICS_TAGS_APLICACAO $METRICS_TAGS_APLICACAO
ENV METRICS_TAGS_AMBIENTE $METRICS_TAGS_APLICACAO
ENV JAEGER_HTTP_SENDER_URL $JAEGER_HTTP_SENDER_URL
ENV JAEGER_PROB_SAMPLING_RATE $JAEGER_PROB_SAMPLING_RATE

COPY --from=builder app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT java -jar app.jar