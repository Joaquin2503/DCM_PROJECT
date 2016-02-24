<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:mb="http://musicbrainz.org/ns/mmd-1.0#">
  <xsl:template match="/">
    <RESULT>
      <xsl:for-each select="*[local-name()='metadata']/*[local-name()='recording-list']/*[local-name()='recording']">
	<xsl:text>&#10;</xsl:text>
	<RECORD>
	  <!--<xsl:text>&#10; &#32;</xsl:text>  <ITEM ANGIE-VAR='?songTitle'><xsl:value-of select="*[local-name()='title']"/></ITEM>-->
	  <xsl:text>&#10; &#32;</xsl:text>  <ITEM ANGIE-VAR='?songId'><xsl:value-of select="@id"/></ITEM>
	  
          <xsl:text>&#10; &#32;</xsl:text>  <ITEM ANGIE-VAR='?artistId'><xsl:value-of select="*[local-name()='artist-credit']/*[local-name()='name-credit']/*[local-name()='artist']/@id"/></ITEM>
          <xsl:text>&#10; &#32;</xsl:text>  <ITEM ANGIE-VAR='?albumId'><xsl:value-of select="*[local-name()='release-list']/*[local-name()='release']/@id"/></ITEM>
        </RECORD>
      </xsl:for-each>  
    </RESULT>
  </xsl:template>
</xsl:stylesheet>
