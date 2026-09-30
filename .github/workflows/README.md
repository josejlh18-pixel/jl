# Jornalera V3

Aplicativo Android nativo para organizar jornadas e ganhos de trabalhadoras diaristas. Esta primeira entrega da V3 é uma experiência offline de alta fidelidade, preparada para receber sincronização, autenticação e agenda remota.

## O que está incluído

- Painel **Hoje**, com resumo mensal de ganhos, jornada atual e próximos trabalhos.
- Ação de iniciar/confirmar jornada com estado visual imediato.
- Histórico de trabalhos e total anual.
- Perfil com atalhos para dados profissionais, notificações e suporte.
- Interface acessível em português, criada com Jetpack Compose e Material 3.

## Requisitos

- Android Studio Ladybug ou posterior.
- JDK 17 ou 21.
- Android SDK Platform 35 e Build Tools correspondentes.

## Executar

```bash
./gradlew :app:assembleDebug
```

Em seguida, instale `app/build/outputs/apk/debug/app-debug.apk` em um emulador ou aparelho com Android 8.0 (API 26) ou mais recente.

## Arquitetura inicial

A interface está concentrada em `MainActivity.kt` para permitir validação rápida do fluxo V3. O próximo passo recomendado é extrair cada destino para uma feature, introduzir `ViewModel`/repositórios e persistir jornadas localmente com Room antes de conectar a API.

## Gerar e baixar o APK pelo navegador

Não é necessário instalar Android Studio, Java ou Gradle no computador. O repositório inclui um workflow do GitHub Actions que compila o APK em um servidor do GitHub.

1. Abra o repositório no GitHub e selecione a aba **Actions**.
2. Abra o workflow **Gerar APK Jornalera V3**.
3. Clique em **Run workflow** e confirme o botão **Run workflow**.
4. Espere o job **Compilar APK debug** ficar verde (normalmente alguns minutos).
5. Abra essa execução e, na seção **Artifacts**, clique em **Jornalera-V3-debug-apk** para baixar o ZIP.
6. Extraia o ZIP: dentro estará o arquivo `app-debug.apk`. Envie-o para o telefone e abra-o para instalar. Se o Android perguntar, permita temporariamente a instalação a partir do app usado para abrir o arquivo.

O artefato fica disponível por 30 dias após cada execução. O workflow é definido em [`.github/workflows/build-apk.yml`](.github/workflows/build-apk.yml).
