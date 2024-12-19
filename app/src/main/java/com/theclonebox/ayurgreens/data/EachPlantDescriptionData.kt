package com.theclonebox.ayurgreens.data

import com.theclonebox.ayurgreens.R

data class EachPlantDescriptionData(
    val img: Int,
    val name: String,
    val shortDescription: String,
    val height: String,
    val lifetime: String,
    val temperature: String,
    val type: String,
    val scientificName: String,
    val conservationStatus: String,
    val longDescription: String,
    val medicinalProperties: List<String>,
    val culturalSignificance: List<String>,
    val historicalSignificance: List<String>,
)

val eachPlantDescriptionData =
    EachPlantDescriptionData(
    img = R.drawable.plant1,
    name = "Peperomia Houseplant",
    shortDescription = "Peperoni is a genus of plants in the family Piperaceae. It is a large and diverse group of small houseplants.",
    height = "6 inches to 3 feet",
    lifetime = "Perennial",
    temperature = "65°F to 75°F",
    type = "Houseplant",
    scientificName = "Peperomia",
    conservationStatus = "Not Evaluated",
    longDescription = "Peperomia is a genus of plants in the family Piperaceae. It is a large and diverse group of small houseplants with waxy and often highly textured leaves. Most Peperomias are compact perennial plants which are grown for their ornamental foliage, rather than their flowers, which are quite unimpressive. They are found in the tropical and subtropical regions of the world, in particular Central America. Peperomias are grown for their ornamental foliage, which comes in a variety of shapes and colors. They are often mistaken for succulents because of their thick, fleshy leaves, but they are not related to cacti or other succulents.",
    medicinalProperties = listOf(
        "1. Peperomia plants are non-toxic to humans and animals.",
        "2. Peperomia plants are known to be air purifiers.",
        "3. Peperomia plants are known to be stress relievers.",
    ),
    culturalSignificance = listOf(
        "1. Peperomia plants are used in Feng Shui to bring good luck and prosperity.",
        "2. Peperomia plants are used in traditional medicine to treat various ailments.",
    ),
    historicalSignificance = listOf(
        "1. Peperomia plants have been cultivated for centuries for their ornamental foliage.",
        "2. Peperomia plants have been used in traditional medicine for centuries.",
    )
    )