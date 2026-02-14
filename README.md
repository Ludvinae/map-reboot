# Map -Reboot-

A modular procedural world generation engine with an integrated editor, designed for experimentation, visualization, and extensible simulation pipelines.

## Overview

Map -Reboot- is a Java-based procedural generation project focused on building a flexible and extensible world simulation pipeline.

The project separates:
- Data (world representation)
- Processing (generation & simulation engine)
- Presentation (editor & rendering tools)

It is designed with a strong emphasis on:
- Stateless generation steps
- Clean architecture and separation of concerns
- UI-driven parameter configuration
- Pipeline-based world processing

## Architecture

The project follows a layered architecture:

**editor  →  core (engine + world)  →  utils**

### 1️⃣ Core
#### core.world

Contains the data model of the simulation:
- World
- Region
- Tile

World configuration structures: this layer is purely data-oriented and contains no UI logic.

#### core.engine

Contains processing logic:
- Generation steps
- Hydrology systems
- Geological transformations
- Execution pipeline

All generation steps are stateless and operate using explicit configuration objects.

### 2️⃣ Editor

The editor layer is responsible for:
- UI components
- Parameter sliders
- Rendering maps (altitude, flow, etc.)
- Orchestrating pipeline execution via an EditorContext

It does not contain generation logic.

### 3️⃣ Utils

Shared utility classes:
- Math helpers
- Memory monitoring
- Generic helpers

## Generation Pipeline

World generation is performed through a pipeline of steps:

**step.apply(world, stepConfig);**


Each step:
- Is stateless
- Receives its configuration explicitly
- Can expose UI parameters (if it is a GenerationStep)
- Does not store internal state

### Step Types

WorldStep → Base interface

GenerationStep<C> → Modifies the world and exposes UI parameters

FeatureStep<C> → Post-processing steps (e.g. rivers) that do not alter base structure

## Parameter System

The editor dynamically generates UI sliders from step configurations.

Each step can provide:
**List<Parameter<?>> createParameters(StepConfig config);**


### Parameters:

- Update the associated StepConfig
- Convert slider values to domain values (linear or logarithmic)
- Remain decoupled from the engine logic

This ensures:
- No UI logic inside the engine
- Config-driven behavior
- Easy extensibility

## Design Principles

- Stateless processing
- Explicit configuration objects
- Clear separation of layers
- No editor dependency inside core
- Pipeline-based execution
- UI-to-config binding via parameters

## Current Features

- Modular geology pipeline
- Hydrology processing
- Altitude and flow rendering
- Logarithmic and linear parameter support
- Memory debugging utilities
- Editor-driven configuration system

## Future Goals

- Biome system
- Climate simulation
- Serialization of world states
- Export tools (images / data)
- Performance optimization
- Unit testing of pipeline steps

## Why "Reboot"?

This project is a structural rewrite and architectural cleanup of an earlier version, focusing on:

- Better modularity
- Cleaner interfaces
- Stronger separation of concerns
- Improved maintainability

## Author

**Benoit Champlon**

Procedural generation enthusiast, backend-oriented developer looking for an internship in France during the summer 2026.