<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:mb="http://musicbrainz.org/ns/mmd-1.0#">
  <xsl:template match="/">
    <table>
    <xsl:for-each select="*[local-name()='metadata']/*[local-name()='release-list']/*[local-name()='release']">
      <tr><td><xsl:value-of select="@id"/></td></tr>
      <tr><td>*<xsl:value-of select="*[local-name()='title']"/>*</td></tr>
    </xsl:for-each>
    </table>
  </xsl:template>
</xsl:stylesheet>
