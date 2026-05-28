# Kessler

FIAP Global Solution 2026.1 — 3º ano Sistemas de Informação

## Sobre o projeto

Kessler é um aplicativo Android que catalogiza detritos espaciais em órbita da Terra e permite ao usuário "adotar" simbolicamente um fragmento para apoiar sua remoção futura. O nome é uma referência à Síndrome de Kessler, cenário teorizado por Donald J. Kessler em 1978 no qual a densidade crescente de objetos em órbita baixa provocaria uma cascata de colisões, tornando regiões inteiras do espaço próximas à Terra inutilizáveis. O app traduz esse problema técnico em uma experiência mobile acessível para o cidadão comum.

## Telas

- **Home** — apresenta a marca PROJETO KESSLER, a tagline "Antes que a cascata comece." e o botão de entrada para o catálogo.
- **Catálogo** — lista 16 detritos rastreados em cards, com estatísticas resumidas e filtros por nível de risco.
- **Detalhe** — exibe identidade, badge de risco, barra de altitude orbital (LEO/MEO/GEO), grade de informações técnicas e histórico do detrito, com botão de adoção.
- **Confirmação** — confirma a adoção simbólica do detrito e oferece atalhos de volta ao catálogo ou ao início.

## Como executar

1. Abrir o projeto no Android Studio Ladybug ou superior.
2. Aguardar a sincronização do Gradle (o wrapper baixa as dependências automaticamente).
3. Selecionar um dispositivo ou emulador rodando Android API 26 (Oreo) ou superior, com conexão à internet ativa para o download da fonte Orbitron pela Google Fonts.
4. Clicar em Run.

## Stack

- Kotlin 2.0.21
- Jetpack Compose (BOM 2024.10.00) com Material 3
- Navigation Compose 2.8.3
- Lifecycle ViewModel Compose 2.8.6
- Tipografia downloadable via Google Fonts (Orbitron e Inter)
- AGP 8.10.1 e Gradle 8.11.1

## Estrutura de pastas

```
app/src/main/java/br/com/fiap/kessler/
├── MainActivity.kt
├── data/
│   ├── model/         (Debris, RiskLevel)
│   └── repository/    (DebrisRepository singleton com 16 detritos)
├── navigation/        (AppNavigation, Routes)
├── ui/
│   ├── components/    (DebrisCard, RiskBadge, FilterChipsRow, OrbitalDecoration, AltitudeBar)
│   ├── screens/       (HomeScreen, CatalogScreen, DetailScreen, ConfirmationScreen)
│   └── theme/         (Color, Type, Theme)
└── viewmodel/         (CatalogViewModel)
```

## Requisitos atendidos

| # | Requisito | Pontos | Onde é atendido |
|---|---|---|---|
| 1 | Tela inicial com nome, descrição e identidade visual | 1,0 | `ui/screens/HomeScreen.kt` |
| 2 | Navegação entre telas via Navigation Compose, mínimo 3 telas | 2,0 | `navigation/AppNavigation.kt` com 4 rotas (HOME, CATALOG, DETAIL, CONFIRMATION) |
| 3 | Uso de Column, Row, Card, LazyColumn, Scaffold | 2,0 | Todas as screens; `DebrisCard`, `CatalogScreen` (LazyColumn + Scaffold), `DetailScreen` (Scaffold + Row/Column), `HomeScreen` (Column) |
| 4 | Exibição de dados relacionados ao tema | 2,0 | `DebrisRepository` com 16 detritos reais + catálogo e detalhe |
| 5 | Interação do usuário (botões, filtros, seleção) | 1,5 | Filtros por risco em `FilterChipsRow`, cards clicáveis, botão "ADOTAR ESTE DETRITO" |
| 6 | Organização do projeto e boas práticas | 1,5 | Separação por camadas (data, ui, navigation, viewmodel), nomes coerentes, código compila sem warnings |
